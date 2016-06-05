# localbitcoins-api

LocalBitcoins documentation: https://localbitcoins.com/api-docs/

This is a Java API for localbitcoins. 

Required Libraries:
- Apache HttpComponents: https://hc.apache.org/downloads.cgi

## Example

LocalBitcoinsRequest request = new LocalBitcoinsRequest(LocalBitcoinsRequest.WALLET, "");
String data = request.pullData();
//Parse String to JSON (I recommend Gson)

To generate hmac_auth_key (key) and hmac_auth_secret (secret), go to: https://localbitcoins.com/accounts/api/
