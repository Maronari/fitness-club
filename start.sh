# Prepare JAR
mvn clean
mvn package -DskipTests

rc=$?
# if maven failed, then we will not deploy new version.
if [ $rc -ne 0 ] ; then
    echo Could not perform mvn clean install, exit code [$rc]; exit $rc
fi

docker volume rm fitness_club_db-data

# Ensure, that docker-compose doesn't exist
docker-compose down

# Start new deployment
docker-compose up --build

