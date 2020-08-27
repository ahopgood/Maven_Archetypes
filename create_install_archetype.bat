ECHO OFF
call mvn clean archetype:create-from-project -X

cd target/generated-sources/archetype

call mvn install -X

cd ../../..