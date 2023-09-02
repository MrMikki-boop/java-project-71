# Makefile
.DEFAULT_GOAL := build-run

run-dist: # Run app
	./build/install/app/bin/app

run:
	./gradlew run

build: # Build app
	./gradlew clean
	./gradlew installDist

report:
		./gradlew jacocoTestReport

lint:
		./gradlew checkstyleMain checkstyleTest

test:
		./gradlew test

last: # Last version plugin
	./gradlew useLatestVersions

build-run: build run-dist

.PHONY: build