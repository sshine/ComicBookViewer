# Comic Book Viewer

This is a small web app for uploading and displaying image slide shows.

The web app has the following API:

```
GET /<author>                 -- gets a list of UUIDs for comics
GET /<author>/<comic>         -- gets a comic viewer (HTML) for a given comic
GET /<author>/<comic>/<page>  -- gets an individual comic page (image file)
```
