TaskManager by Ioannis Mittas

Based in Model-View-ViewModel architecture. 

Used Android Architecture Components to implements SQLite Database for local storage (Room, Livedata, Viewmodel).

Screen 1 consists of a MainActivity with two fragments, one for pending tasks and one for completed tasks.Each fragment has a recyclerview to organize the tasks. In order to implement the fragments in two tabs, but still have their recyclerviews swipable, I used a NonSwipeableViewPager. The user can navigate between tabs by tapping on them. In order to be swipeable, the adapters of the recyclerviews implement an ItemTouchHelperAdapter. Also, the adapters each have a LongClickListener, so if a user long presses on a task , the edit screen opens.A floating button in the bottom right give the ability to navigate to Screen 2. 

Screen 2 consists of an AddActivity. I used a custom open source filepicker in order to choose files. The button to choose files is on the action bar. 

To implement file uploading, Dropbox API v2 was used. The tasks can be uploaded concurrently, and that is achieved with the use of a Service for background execution(UploadService), where each uploading task runs in each own Thread().

In order to complete the project in time, there wasn't implemented the notification mechanism as asked, a notification appears but it's simple. Also, the list of keywords weren'n implemented. Finally, although the listeners are there, 
the wasn't implements an edit screen. As I understood, the edit screen is different from add screen. 
The three above tasks weren't implemented in order to beat the deadline of 4 days. :) 