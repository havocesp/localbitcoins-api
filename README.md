# localbitcoins-api

LocalBitcoins documentation: https://localbitcoins.com/api-docs/

This is a Java API for localbitcoins. 

## Example

```Java
LocalBitcoinsRequest request = new LocalBitcoinsRequest(LocalBitcoinsRequest.WALLET, null, LocalBitcoinsRequest.HttpType.GET);
String data = request.pullData();
//Parse String to JSON (I recommend Gson)
```
To use parameters you have to create a ParameterCollection object and add BasicNameValuePair's to it.
```Java
ParameterCollection parameterCollection = new ParameterCollection(new ArrayList<>());
parameterCollection.add(new BasicNameValuePair("test", "1234"));
LocalBitcoinsRequest request = new LocalBitcoinsRequest("/api/sample", parameterCollection, LocalBitcoinsRequest.HttpType.POST);
String data = request.pullData();
//Parse String to JSON (I recommend Gson)
```

To generate hmac_auth_key (key) and hmac_auth_secret (secret), go to: https://localbitcoins.com/accounts/api/
