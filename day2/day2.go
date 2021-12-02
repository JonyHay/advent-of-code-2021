package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func main() {

	file, err := os.Open("input.txt")
	if err != nil {
		log.Fatal(err)
		os.Exit(2)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	var depth, distance, aim int
	for scanner.Scan() {

		parts := strings.Split(scanner.Text(), " ")
		fmt.Println(parts[0], parts[1])

		amount, err := strconv.Atoi(parts[1])
		if err != nil {
			log.Fatal(err)
			os.Exit(2)
		}

		switch parts[0] {
		case "down":
			// down X increases your aim by X units.
			aim += amount
		case "up":
			// up X decreases your aim by X units.
			aim -= amount
		case "forward":
			// forward X does two things:
			// - It increases your horizontal position by X units.
			distance += amount
			// - It increases your depth by your aim multiplied by X.
			depth += (aim * amount)
		}

	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	fmt.Println("Total distance", distance)
	fmt.Println("Final depth", depth)

	var answer int = depth * distance
	fmt.Println("depth * distance = ", answer)

}
