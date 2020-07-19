#!/bin/bash

CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o markdown_linux_amd64
CGO_ENABLED=0 GOOS=darwin GOARCH=amd64 go build -o markdown_darwin_amd64 
CGO_ENABLED=0 GOOS=windows GOARCH=amd64 go build -o markdown_windows_amd64.exe

