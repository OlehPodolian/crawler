# crawler

java -cp ./target/crawler.jar Crawler <element_id> (Optional) string ... filePaths

e.g.

1. with search element 
java -cp ./target/crawler.jar Crawler wrapper /...s/startbootstrap-sb-admin-2-examples/sample-1-evil-gemini.html

where "wrapper" is search-element-id


2. with default search element 
java -cp ./target/crawler.jar Crawler /.../startbootstrap-sb-admin-2-examples/sample-1-evil-gemini.html 


2. with default search element  and many files
java -cp ./target/crawler.jar Crawler /.../startbootstrap-sb-admin-2-examples/sample-1-evil-gemini.html  /.../startbootstrap-sb-admin-2-examples/sample-2-evil-gemini.html 
 
