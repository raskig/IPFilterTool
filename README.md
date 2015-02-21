# IPFilterTool

Demo implementation of Spring Tool Suite for validating permitted ip addresses based on a configuration.

The configuration contains several range of IP addresses from which we will accept connections.
The range configuration can be ip range (from-ip - to-ip) or
it can be a wildcarded ip address.

Example config:

188.221.174.101 - 188.221.174.199

82.165.97.11 - 82.165.97.79

82.132.141.*

The service validates whether any given IP address is a permitted client.
 
#IIpFilterServiceImpl
The service can be accessed trough the IIpFilterService.validateIpAddress(String ipAddress); call.
If the given ip address is not valid, ForbiddenIpAddressException is thrown.
 
The permitted IP address ranges can be stored in a relational database or in redis based on the used DAO implementation.

#Running REDIS tests:

For running tests you need to install and run redis:
  http://redis.io/topics/quickstart
  For using a remote redis instance: Customize app-context.xml if necessary.

#Using custom Relational database:
  Customize app-context.xml <!-- JDBC settings --> section. (Current configuration is for derbby.) 
  Create database. Example SQL for setting up derby database:
  
  https://github.com/raskig/IPFilterTool/blob/master/src/main/java/com/silvertree/ipfilter/dao/sql/db-schema.sql
  
  Derby howto: http://db.apache.org/derby/quick_start.html
  
#Adding filter config lines to database:
  
  Use IpFilterDao implementations
  
