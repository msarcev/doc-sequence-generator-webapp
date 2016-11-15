##Sequence number generator web applicaiton
#AG04 candidate testing

### Requirements
* [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Postgres](https://www.postgresql.org//)
* [PGadmin](https://www.pgadmin.org/download/)
* [MyBatis](http://www.mybatis.org/mybatis-3/)
* [Tomcat](https://tomcat.apache.org/download-80.cgi) - or other applications server of your choice (not mandatory see Spring Boot run)


## Database setup
### Postgres setup

1. Open pgAdmin III
2. Create database named "ag04" (if it doesn't exist or the one of Your choice)
3. Create new login role. Righ-click on "Login Roles" and choose “New Login Roles” Under "Login Roles" create "New Login Role..."
4. On "Properties" type "seq-gen-user" in "Role name"
5. On "Definition" tab type "seq-gen-pwd" in "Password" and "Password (again)" fields
6. On "Variables" tab type "search_path" in "Variable Name" field. Type "seqnumgen" in "Variable Value" field. Choose "seq-num-gen" from "Database" dropdown. (“-“ is not supported in value of the “Variable name”)
7. Click "Add/Change" button
8. Click "OK"
9. Create new Schema names: "seqnumgen" and set "seq-gen-user" as its owner.