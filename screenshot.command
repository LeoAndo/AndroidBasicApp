#!/bin/bash

# command実行前の準備
# 1. Android デバイス(エミュレータ or 実機)1台のみ起動してください

# adb のパスを通す
export PATH=$PATH:/Users/$USER/Library/Android/sdk/platform-tools

# カレントディレクトリをファイルのある場所に移動する
cd `dirname $0`

# アプリのインストール
./gradlew clean installSimpleDebug
./gradlew clean installBottombarDebug
./gradlew clean installDrawerDebug

# ダークモード(自動でOFFにしない): ON
adb shell "cmd uimode night yes"

# simpleバージョン
# アプリ起動 (sleepで4秒処理待ちする. Activity起動に時間かかるので)
adb shell am start -n com.template.androidbasicapp.simple/com.template.androidbasicapp.MainActivity; sleep 4;
 
# トップ画面のscreenshotを撮り、プロジェクトのcaptureディレクトリ配下にコピーする
adb shell screencap /sdcard/Download/simple_apixx_dark.png
adb pull /sdcard/Download/simple_apixx_dark.png capture

# bottombarバージョン
adb shell am start -n com.template.androidbasicapp.bottombar/com.template.androidbasicapp.MainActivity; sleep 4;
adb shell screencap /sdcard/Download/bottombar_apixx_dark.png
adb pull /sdcard/Download/bottombar_apixx_dark.png capture

# drawerバージョン
adb shell am start -n com.template.androidbasicapp.drawer/com.template.androidbasicapp.MainActivity; sleep 4;
adb shell screencap /sdcard/Download/drawer_apixx_dark.png
adb pull /sdcard/Download/drawer_apixx_dark.png capture


# ダークモード(自動でONにしない): OFF
adb shell "cmd uimode night no"

# simpleバージョン
adb shell am start -n com.template.androidbasicapp.simple/com.template.androidbasicapp.MainActivity; sleep 4;
adb shell screencap /sdcard/Download/simple_apixx.png
adb pull /sdcard/Download/simple_apixx.png capture

# bottombarバージョン
adb shell am start -n com.template.androidbasicapp.bottombar/com.template.androidbasicapp.MainActivity; sleep 4;
adb shell screencap /sdcard/Download/bottombar_apixx.png
adb pull /sdcard/Download/bottombar_apixx.png capture

# drawerバージョン
adb shell am start -n com.template.androidbasicapp.drawer/com.template.androidbasicapp.MainActivity; sleep 4;
adb shell screencap /sdcard/Download/drawer_apixx.png
adb pull /sdcard/Download/drawer_apixx.png capture


# ダークモード(日の入りに自動的にON): OFF
# adb shell "cmd uimode night auto"


# apkファイルの格納ディレクトリを開く
open capture/

# コマンド終了時にターミナルを閉じる
osascript -e 'tell application "Terminal" to quit' & exit