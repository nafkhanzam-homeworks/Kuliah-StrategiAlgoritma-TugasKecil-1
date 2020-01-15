if [ -d "./bin/" ]; then
    rm -r ./bin/*
fi
javac -d ./bin/ $(find ./src/* | grep .java)