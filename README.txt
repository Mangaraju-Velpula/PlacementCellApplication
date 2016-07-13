@Placement Cell Application-v1
whenever you install the app you need to sync with amazon database that sync can be done by click on cloud symbol at Application top right corner, wait for 15sec and slide to another tab and Data will be loaded.

Bug in Application

	Till upcomming Events Application work normally. Whenever you visited to JNFs and Results it will abruptly close the application. 


Bug Solution : 
	
	We use Amazon S3 bucket to store *.pdf,*.jpg,*png,*jpeg files. whenever you visit JNFs or Results a request will be sent to Amazon S3 bucket this request should be perform in AsynchronousTask class but we didnt use that class so a loop of requests for s3 bucket is generated when visting those tabs that make app to close abruptly
