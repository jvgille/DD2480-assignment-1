
bin:
	mkdir bin

clean: bin
	rm -r bin/*

build: bin
	javac -d bin -cp junit-platform-console-standalone-1.3.2.jar src/main/* src/test/*

test: build
	java -jar junit-platform-console-standalone-1.3.2.jar -cp bin/ --scan-classpath
