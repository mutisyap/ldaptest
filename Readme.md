# What is this?
- A simple client to connect to ldap using java, maven and unboundsdk

# Build
``mvn clean package``
# Run
## 1. Replace credentials in file and rebuild then run
``java -cp "target/*:target/libs/*" io.pmutisya.App ``

## OR
## 2. pass your credentials as system variables(D arguments)

``java -Dhost=hostname -Dport=389 -Dusername=username -Dpassword=passowrd -cp "target/*:target/libs/*" io.pmutisya.App ``
