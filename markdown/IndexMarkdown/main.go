package main

import (
	"bufio"
	"bytes"
	"flag"
	"log"
	"os"
	"path/filepath"
	"strings"
	"unicode"
)

func readLines(path string) ([]string, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}
	return lines, scanner.Err()
}

func getMarkdownTitle(lines []string) string {
	for _, line := range lines {
		line = strings.TrimSpace(line)
		if len(line) <= 0 {
			continue
		}

		var arr [3]rune
		var index int
		var c rune
		for index, c = range line {
			arr[index] = c
			if index >= 2 {
				break
			}
		}

		start := 0
		if index >= 0 {
			if arr[0] == '#' {
				start = 1
				if unicode.IsSpace(arr[1]) {
					start = 2
				}
			}
		}
		return strings.TrimSpace(line[start:])
	}
	return ""
}

func findMarkdownFiles(path string) []string {
	var list []string
	filepath.Walk(path, func(path string, info os.FileInfo, err error) error {
		if err != nil {
			return err
		}
		if info.IsDir() == false && strings.HasSuffix(strings.ToLower(info.Name()), ".md") {
			list = append(list, path)
		}
		return nil
	})
	return list
}

var escapeChars = []byte("\\`*_{}[]()#+-.!:|&<>~")

func escapeText(str string) string {
	if len(str) <= 0 {
		return str
	}

	var escapeBytes bytes.Buffer
	for _, b := range []byte(str) {
		indexByte := bytes.IndexByte(escapeChars, b)
		if indexByte >= 0 {
			escapeBytes.WriteByte('\\')
			escapeBytes.WriteByte(escapeChars[indexByte])
		} else {
			escapeBytes.WriteByte(b)
		}
	}
	return escapeBytes.String()
}

func main() {
	markdownDir := flag.String("d", "", "markdown dir")
	flag.Parse()

	if _, err := os.Stat(*markdownDir); err != nil {
		log.Fatalf("parse:\n %s \n %s", err)
	}

	var markdown bytes.Buffer
	markdown.WriteString("# 文档列表\n\n")
	newFilePath := filepath.Join(*markdownDir, "X.md")
	var file *os.File
	var err error
	if _, err = os.Stat(newFilePath); os.IsNotExist(err) {
		file, err = os.Create(newFilePath)
		if err != nil {
			log.Fatalln(err)
		}
	} else {
		file, err = os.OpenFile(newFilePath, os.O_WRONLY, os.FileMode(0644))
		if err != nil {
			log.Fatalln(err)
		}
		_ = file.Truncate(0)
		_, _ = file.Seek(0, 0)
	}

	defer func() {
		_ = file.Sync()
		_ = file.Close()
	}()

	list := findMarkdownFiles(*markdownDir)
	for _, l := range list {
		lines, err := readLines(l)
		if err != nil {
			log.Fatalf("read line: %s", err)
		}
		title := getMarkdownTitle(lines)
		if len(title) <= 0 {
			title = filepath.Base(l)
		}
		markdown.WriteString("+ [")
		markdown.WriteString(escapeText(title))
		markdown.WriteString("]")
		markdown.WriteString("(")
		relPath, _ := filepath.Rel(*markdownDir, l)
		markdown.WriteString(relPath)
		markdown.WriteString(")")
		markdown.WriteString("\n\n")
	}

	_, _ = file.Write(markdown.Bytes())
}
