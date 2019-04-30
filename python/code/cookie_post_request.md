### Cookie + HTTP POST request

```python

    # cookie
    cookie_jar = cookielib.CookieJar()
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cookie_jar))
    urllib2.install_opener(opener)

    request = urllib2.Request('http://......')

    # data="" 表示这是一个 POST 请求
    result = urllib2.urlopen(request, data="")
    print result
```