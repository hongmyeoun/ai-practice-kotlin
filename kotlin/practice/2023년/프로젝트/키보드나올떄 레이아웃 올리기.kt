textfield를 사용하면 버튼이 밑에 있을경우 textfield를 클릭하면 키보드가 올라오면서 버튼을 누를수 없게 된다.
또 textfield가 길어지면 scroll할수 있지만 화면이 고정되어있어서 글이 안보이게 된다. 
이를 해결하기위해선 간단하게 mainfest에 값을 추가해주면된다

이값을 Manifest에 사용할 activity에 넣어주면 된다.
android:windowSoftInputMode="adjustResize"

AndroidManifest.Xml

        //여긴 안씀
        <activity
            android:name=".ShowTextPage"
            android:exported="false" />
        
        <activity
            android:name=".EdittingPage"
            //여긴 필요해서씀
            android:windowSoftInputMode="adjustResize"
            android:exported="false" />
  
        <activity
            android:name=".NewNotePage"
            //여긴 필요해서씀
            android:windowSoftInputMode="adjustResize"
            android:exported="false" />
  
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.NoteProject">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
