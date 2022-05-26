[![](https://jitpack.io/v/Renlov/ShadowLibrary.svg)](https://jitpack.io/#Renlov/ShadowLibrary)

# Shadow vector image (for Android app on Kotlin)

This library can help you to set shadow on vector. Works on API level 23 or later.
Your image **(Vector)** will duplicate and bluring.

![ShadowLib](https://i.ibb.co/kyZ3F8y/photo-2022-04-27-15-54-14.jpg "ShadowLib")

### Usage

-Dependency
Add your app level build.gradle
```Xml
  implementation 'com.github.Renlov:ShadowLibrary:0.1.0'
```
Add in your settings.gradle
```Xml
 allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```



### Usage in code
Add in your xml shadowvectorimage
```Xml
    <com.hedgehog.shadowLibrary.ShadowLibrary
        android:id="@+id/shadowLib"
        android:layout_width="124dp"
        android:layout_height="124dp"/>
```

Add in your code `ShadowLibrary` object
```kotlin
  ShadowLibrary.load(R.drawable.ic_resource_default)
            .withShadowColor(R.color.shadow)
            .withShadowRadius(15)
            .withShadowScale(1.1f)
            .withShadowTransition(top = 5, start = 5)
            .into(binding.shadowLib)
```
### Usage in xml
```xml

xmlns:app="http://schemas.android.com/apk/res-auto"
						 
<com.hedgehog.shadowLibrary.ShadowLibrary
        android:id="@+id/shadowLib"
        android:layout_width="124dp"
        android:layout_height="124dp"
        app:shadowImageLib="@drawable/ic_resource_default"
        app:shadowPaddingLeftLib="10"
        app:shadowRadiusLib="15"
        app:shadowScaleLib="1.1"
        app:shadowTransitionLeftLib="5"
        app:shadowTransitionTopLib="5" />
```
### Parameters
| Param | Description | defValue |
|----------------|----------------|----------------|
| shadowRadiusLib | Its value for set shadow blur for your image beetween 1 to 25| 1 (Int) |
| shadowScaleLib | scale shadow relatively your image | 1f (Float) |
| shadowColorLib | set shadow color from R.color.yourColor | R.color.shadow(#DCDCDC, Int) |
| shadowPaddingTopLib | set padding top relatively to shadow | 0 (Int) | 
| shadowPaddingBottomLib | set padding bottom relatively to shadow | 0 (Int) | 
| shadowPaddingLeftLib | set padding left relatively to shadow | 0 (Int) | 
| shadowPaddingRightLib | set padding right relatively to shadow | 0 (Int) | 
| shadowTransitionTopLib | move shadow top relatively to image | 0 (Int) | 
| shadowTransitionBottomLib | move shadow bottom relatively to image | 0 (Int) | 
| shadowTransitionLeftLib | move shadow left relatively to image | 0 (Int) | 
| shadowTransitionRightLib | move shadow right relatively to image | 0 (Int) | 

### License
```
Designed and developed by 2022 Renlov, res1-dent (Denis Pimenov, Andrey Sazankov)

Licensed under the Apache License, Version 3.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-3.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
