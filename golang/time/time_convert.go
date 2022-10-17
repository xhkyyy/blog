package main

import (
	"fmt"
	"time"
)

func main() {
	fmt.Println("string time nowtime.Now() -> ", time.Now().String())

	fmt.Println("MM-DD-YYYY -> ", time.Now().Format("01-02-2006"))

	fmt.Println("YYYY-MM-DD -> ", time.Now().Format("2006-01-02"))

	fmt.Println("YYYY.MM.DD -> ", time.Now().Format("2006.01.02 15:04:05"))

	fmt.Println("YYYY#MM#DD -> ", time.Now().Format("2006#01#02"))

	fmt.Println("YYYY-MM-DD hh:mm:ss time.Now() -> ", time.Now().Format("2006-01-02 15:04:05"))

	fmt.Println("Time with MicroSecondstime.Now() -> ", time.Now().Format("2006-01-02 15:04:05.000000"))

	fmt.Println("Time with NanoSecondstime.Now() -> ", time.Now().Format("2006-01-02 15:04:05.000000000"))

	t, _ := time.Parse("2006-01-02 15:04:05", "2022-01-01 12:00:00")

	fmt.Println("ShortNum Month time.Now() -> ", t.Format("2006-1-02"))

	fmt.Println("LongMonth time.Now() -> ", time.Now().Format("2006-January-02"))

	fmt.Println("ShortMonth time.Now() -> ", time.Now().Format("2006-Jan-02"))

	fmt.Println("ShortYear time.Now() -> ", time.Now().Format("06-Jan-02"))

	fmt.Println("LongWeekDay time.Now() -> ", time.Now().Format("2006-01-02 15:04:05 Monday"))

	fmt.Println("ShortWeek Day time.Now() -> ", time.Now().Format("2006-01-02 Mon"))

	fmt.Println("ShortDay time.Now() -> ", time.Now().Format("Mon 2006-01-2"))

	fmt.Println("Short Hour Minute Secondtime.Now() -> ", time.Now().Format("2006-01-02 3:4:5"))

	fmt.Println("Short Hour Minute Secondtime.Now() -> ", time.Now().Format("2006-01-02 3:4:5 PM"))

	fmt.Println("Short Hour Minute Secondtime.Now() -> ", time.Now().Format("2006-01-02 3:4:5 pm"))
}
