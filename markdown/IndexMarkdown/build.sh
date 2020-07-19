#!/bin/bash

CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o link-markdown-linux-amd64
CGO_ENABLED=0 GOOS=darwin GOARCH=amd64 go build -o link-markdown-darwin-amd64
CGO_ENABLED=0 GOOS=windows GOARCH=amd64 go build -o link-markdown-windows-amd64.exe

