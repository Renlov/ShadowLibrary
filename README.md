[![](https://jitpack.io/v/Renlov/ShadowLibrary.svg)](https://jitpack.io/#Renlov/ShadowLibrary)

# Shadow vector image (Kotlin)

This library can help you to set shadow on vector. Works on API level 23 or later.
Your image **(Vector)** will duplicate and bluring.

![ShadowLib](https://i.ibb.co/kyZ3F8y/photo-2022-04-27-15-54-14.jpg "ShadowLib")

### Usage

-Dependency
Add your app level build.gradle
```Xml
  implementation 'com.github.Renlov:ShadowLibrary:0.0.6'
```
Add your app level build.gradle
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
    <com.hedgehog.shadowlibrary.ShadowLib
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
<com.hedgehog.shadowlibrary.ShadowLib
        android:id="@+id/shadowLib"
        android:layout_width="124dp"
        android:layout_height="124dp"
        app:shadowImage="@drawable/ic_resource_default"
        app:shadowPaddingLeft="10"
        app:shadowRadius="15"
        app:shadowScale="1.1"
        app:shadowTransitionLeft="5"
        app:shadowTransitionTop="5" />
```
### Parameters
| Param | Description | defValue |
|----------------|----------------|----------------|
| shadowRadius | Its value for set shadow blur for your image beetween 1 to 25| 1 (Int) |
| shadowScale | scale shadow relatively your image | 1f (Float) |
| shadowColor | set shadow color from R.color.yourColor | R.color.shadow(#DCDCDC, Int) |
| shadowPaddingTop | set padding top relatively to shadow | 0 (Int) | 
| shadowPaddingBottom | set padding bottom relatively to shadow | 0 (Int) | 
| shadowPaddingLeft | set padding left relatively to shadow | 0 (Int) | 
| shadowPaddingRight | set padding right relatively to shadow | 0 (Int) | 
| shadowTransitionTop | move shadow top relatively to image | 0 (Int) | 
| shadowTransitionBottom | move shadow bottom relatively to image | 0 (Int) | 
| shadowTransitionLeft | move shadow left relatively to image | 0 (Int) | 
| shadowTransitionRight | move shadow right relatively to image | 0 (Int) | 

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
