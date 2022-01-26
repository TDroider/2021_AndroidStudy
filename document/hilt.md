# Hilt
## 本日の目的
* Dagger Hiltの簡単な使い方を理解する

今回の資料は以下を参考にさせていただいています。
https://developer.android.com/training/dependency-injection/hilt-android?hl=ja

## [Hilt](https://dagger.dev/hilt/)とは
* Dagger上に構築されたDIライブラリ
* Android開発に特化

### メリット
* Componentを独自に定義する必要がない(Scope)
* ViewModelのDIが簡単
-> ボイラープレートが削減できる

### デメリット
* Hiltの制約に従わないといけないので自由度は減る

## 使ってみよう
前回の使用した構成を元に使ってみます

※前回の資料は[こちら](https://scrapbox.io/2021AndroidStudy/DI(DependencyInJection))

<img src="https://developer.android.com/images/training/dependency-injection/4-application-graph.png?hl=ja" width="320" />


### 1. 依存関係を追加
- build.gradle

```groovy:build.gradle
buildscript {
    ...
    dependencies {
        ...
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.28-alpha'
    }
}
```

- app/build.gradle
```groovy:app/build.gradle
apply plugin: 'kotlin-kapt'
apply plugin: 'dagger.hilt.android.plugin'

dependencies {
    implementation "com.google.dagger:hilt-android:2.28-alpha"
    kapt "com.google.dagger:hilt-android-compiler:2.28-alpha"
}
```

### 2. Applicationクラスに **@HiltAndroidApp** アノテーションをつける

```kotlin
@HiltAndroidApp
class MyApplication: Application() { ... }
```

@HiltAndroidAppアノテーションをつけることでHiltのコード生成をトリガーします。

### 3. Androidのコンポーネントに **@AndroidEntryPoint** アノテーションをつける

今回の例だとLoginActivityにアノテーションをつけます。

```kotlin
@AndroidEntryPoint
class LoginActivity: Activity() {

    @Inject lateinit var loginViewModel: LoginViewModel
    ...
}
```

@AndroidEntryPointアノテーションをつけることで、Hiltの管理下になりDIが実行されます。
これにより、フィールドインジェクションを行うことができます


※ Hiltでは以下をサポートしています。
* Activity
* Fragment
* View
* Service
* BroadcastReceiver

Android クラスに @AndroidEntryPoint アノテーションを付ける場合は、それに依存する Android クラスにもアノテーションを付ける必要があります。たとえば、フラグメントにアノテーションを付ける場合は、そのフラグメントを使用するアクティビティにもアノテーションを付ける必要があります。

### 4. 〇〇DataSource, Repository
Daggerと同様のため、省略します

### 5. Component, Module
Daggerでは以下のように書いていたと思います

NetworkModuleをApplicationComponentに登録
-> ApplicationComponentをApplicationに登録
```kotlin
@Module
class NetworkModule { ... }

@Component
interface ApplicationComponent { ... }

class MyApplication: Application() {
     // Reference to the application graph that is used across the whole app
     val appComponent = DaggerApplicationComponent.create()
 }
```

Hiltでは以下のように書きます

```kotlin
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    ...
}
```

### 6. ViewModel
Daggerの場合だと、AACのViewModelをInjectしたい場合にRepositoryのような`Module`を作ったり、ViewModelの`Factory`を作ったりと面倒なことがありました。

https://github.com/android/architecture-samples/tree/dev-dagger

Hiltでは、ModuleやFactoryを作らずとも`@HiltViewModel`アノテーションをつけるだけでよくなりました。

※ 画面からViewModelに渡したいものがあれば別途Factoryを作る必要があります。

https://star-zero.medium.com/viewmodel%E3%81%A7assistedinject%E3%82%92%E4%BD%BF%E3%81%86-1bb375f6cd48

```kotlin
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() { ... }
```

ActivityやFragmentではViewModelProviderを用いるか、KTX拡張機能の`by viewModels()`でViewModelのインスタンスを取得することができます

```kotlin
@AndroidEntryPoint
class LoginActivity: Activity() {

    private val loginViewModel: LoginViewModel by viewModels()
    ...
}
```

参考:
- https://dagger.dev/hilt/
- https://zenn.dev/rmakiyama/articles/ed007c13e498fc
