Dropbox Upload Manager by Ioannis Mittas

Android app to upload files to Dropbox. 

The user can create tasks to upload files. Each task that the user creates consist of a name, a description, and the file to be uploaded. 
After a task is created, it's added on the list of pending tasks. Then, the user can swipe right on a task to immediately start uploading the corresponding file or swipe left 
to start uploading the file after one minute. The tasks can be executed in parallel, so the user can start uploading many files simultaneously. After the task is completed, it's 
added on the list of completed tasks. If the user swipe any task in the completed tasks list, it's added on the pending tasks list again. 

Based in Model-View-ViewModel architecture. 

Used Android Architecture Components to implements SQLite Database for local storage (Room, Livedata, Viewmodel).

Screen 1 consists of a MainActivity with two fragments, one for pending tasks and one for completed tasks.Each fragment has a recyclerview to organize the tasks. In order to implement 
the fragments in two tabs, but still have their recyclerviews swipable, I used a NonSwipeableViewPager. The user can navigate between tabs by tapping on them. In order to be 
swipeable, the adapters of the recyclerviews implement an ItemTouchHelperAdapter. A floating button in the bottom right give the ability to navigate to Screen 2. 

Screen 2 consists of an AddActivity. I used a custom open source filepicker in order to choose files. The button to choose files is on the action bar. 

To implement file uploading, Dropbox API v2 was used. The tasks can be uploaded concurrently, and that is achieved with the use of a Service for background execution(UploadService), where each uploading task runs in each own Thread().

TODO:Each adapter has a LongClickListener. Implement function that if a user long presses on a task , an edit screen opens in order to edit the task.