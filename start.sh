# Prepare JAR
mvn clean
mvn package

rc=$?
# if maven failed, then we will not deploy new version.
if [ $rc -ne 0 ] ; then
    echo Could not perform mvn clean install, exit code [$rc]; exit $rc
fi

# Ensure, that docker-compose stopped
docker-compose stop

# Start new deployment
docker-compose up --build -d

