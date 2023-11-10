#!/bin/bash

# カレントディレクトリをファイルのある場所に移動する
cd `dirname $0`

# echoの赤字設定
ESC=$(printf '\033') # \e や \x1b または $'\e' は使用しない

# 接続先を選ぶ
echo "Select the buildType. 1: debug 2: release"
read buildType

# クリーンビルドしてapkファイルを作成する
case $buildType in
  1) ./gradlew clean assembleDebug ;;
  2) ./gradlew clean assembleRelease ;;
  *)
    echo "${ESC}[31mPlease choose 1 or 2. build apk failed.${ESC}[m"
    exit 1
    ;;
esac

# apkファイルの格納ディレクトリを開く
open app/build/outputs/apk/

# コマンド終了時にターミナルを閉じる
osascript -e 'tell application "Terminal" to quit' & exit