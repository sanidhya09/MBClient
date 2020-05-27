package com.sandy.mbclient;

public interface Constants {

	// TODO Change it to your web domain
    String WEB_DOMAIN = "zoom.us";

	// TODO Change it to your APP Key
    String SDK_KEY = "gWwQDKTJBt1uozkoZKuY3Ja9VW7NKOn8Wbb0";
	
	// TODO Change it to your APP Secret
    String SDK_SECRET = "1Bk7Cel5N1UfG6mUJsUP3AQfUAzvmf2kzBAS";

	// TODO change it to your user ID
    String USER_ID = "https://api.zoom.us/v2/users/{sanidhya09@gmail.com}";//https://api.zoom.us/v2/users/{sanidhya09@gmail.com}
	
	// TODO change it to your token
    String ZOOM_TOKEN = "eyJhbGciOiJIUzUxMiIsInYiOiIyLjAiLCJraWQiOiJjNTFiNjk3ZS1iZmRmLTQ1ZTQtOTFlZC03OWM2ZjhkMGQwMjIifQ.eyJ2ZXIiOiI2IiwiY2xpZW50SWQiOiJzMUFTX0hPcFN0V1J5SXpUUUtLVnZ3IiwiY29kZSI6Ilk4YmFWZ2E2QmtfWHBBbmd2MUhUenU3OWZrT2ZUcGpMdyIsImlzcyI6InVybjp6b29tOmNvbm5lY3Q6Y2xpZW50aWQ6czFBU19IT3BTdFdSeUl6VFFLS1Z2dyIsImF1dGhlbnRpY2F0aW9uSWQiOiJhM2JiYjQ5MDhlYWEwODAzZjZiZTdiNzU1ZTI3NjJkYSIsInVzZXJJZCI6IlhwQW5ndjFIVHp1Nzlma09mVHBqTHciLCJncm91cE51bWJlciI6MCwiYXVkIjoiaHR0cHM6Ly9vYXV0aC56b29tLnVzIiwiYWNjb3VudElkIjoiSG85YUFJYm5UekNyYmFCZlM0RWYzZyIsIm5iZiI6MTU4OTc3NzY0NiwiZXhwIjoxNTg5NzgxMjQ2LCJ0b2tlblR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJpYXQiOjE1ODk3Nzc2NDYsImp0aSI6IjA2YTNkMDA4LTA3MTItNDZkZi1iMjBjLWNlOThjZDU0ZDRkMCIsInRvbGVyYW5jZUlkIjowfQ.W88_3Ik2FDTmQcDMAcWxpZQMk0lOf2rDKPpUY7rgYQV3anKyL8PvY_hV126tSv5YhQubWVSchVlwhlQEmK2oLg";
	
	// TODO Change it to your exist meeting ID to start meeting
    String MEETING_ID = "8405341943";

//    Join Zoom Meeting
//    https://us04web.zoom.us/j/4832831361?pwd=Y3IyMHZKd1ZkamxBUkNVRUFpOWdIUT09
//
//    Meeting ID: 483 283 1361
//    Password: 9Lg7jX


    /**
     * We recommend that, you can generate jwttoken on your own server instead of hardcore in the code.
     * We hardcore it here, just to run the demo.
     *
     * You can generate a jwttoken on the https://jwt.io/
     * with this payload:
     * {
     *     "appKey": "string", // app key
     *     "iat": long, // access token issue timestamp
     *     "exp": long, // access token expire time
     *     "tokenExp": long // token expire time
     * }
     */
//    public final static String SDK_JWTTOKEN = YOUR JWTTOKEN;

}
