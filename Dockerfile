FROM websphere-liberty:kernel

COPY --chown=1001:0 src/main/liberty/config/server.xml /config/server.xml
COPY --chown=1001:0 /src/main/liberty/config/server.env /config/server.env
COPY --chown=1001:0 /src/main/liberty/config/jvm.options /config/jvm.options
COPY --chown=1001:0 /target/acmeair-flightservice-java-3.0.war /config/apps/

RUN configure.sh || if [ $? -ne 22 ]; then exit $?; fi


