# Makefile
.DEFAULT_GOAL := build-run
.PHONY: build

run-dist: # Run app
	./build/install/app/bin/app

run:
	./gradlew run

build: # Build app
	./gradlew clean
	./gradlew installDist

lint: # Check code style
	./gradlew checkstyleMain checkstyleTest

test: # Test code
	./gradlew test

last: # Last version plugin
	./gradlew useLatestVersions

build-run: build run-dist