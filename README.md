# Coffe Machine

### Setup
1. Run your consul (with you prefer in local: consul agent -dev -bind 127.0.0.1)
2. Set parameters:
  * curl -X PUT -d espresso http://localhost:8500/v1/kv/config/CoffeeMachine/coffee-type
  * curl -X PUT -d 5 http://localhost:8500/v1/kv/config/CoffeeMachine/preparing-rate
  * curl -X PUT -d 1 http://localhost:8500/v1/kv/config/CoffeeMachine/machine-version

3. Your consul should be like this:
![alt tag](http://i.imgur.com/oIRWGnC.png)

4. Run the Coffee Machine and change your consul KV's whenever you want!

5. Enjoy your coffees without new deploys :D


