//gradle(project)

plugins {
    id 'com.android.application' version '8.0.2' apply false
    id 'com.android.library' version '8.0.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.8.0' apply false
    id 'com.google.devtools.ksp' version '1.8.0-1.0.8' apply false
}

//gradle(Module)
def room_version = "2.5.0"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

    ksp "androidx.room:room-compiler:$room_version"
composeOptions {
        kotlinCompilerExtensionVersion '1.4.1'
    }
