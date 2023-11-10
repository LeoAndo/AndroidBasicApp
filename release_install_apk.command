#!/bin/bash

# カレントディレクトリをファイルのある場所に移動する
cd `dirname $0`

# echoの赤字設定
ESC=$(printf '\033') # \e や \x1b または $'\e' は使用しない

# 接続先を選ぶ
echo "Select the BuildType. 1: debug 2: release"
read type

# 接続先に応じてBuildTypeを決める
case $type in
  1) buildType="Debug" ;;
  2) buildType="Release" ;;
  *)
    echo "${ESC}[31mPlease choose 1 or 2.${ESC}[m"
    exit 1
    ;;
esac

# インストールするアプリを選ぶ
echo "Select the app. 1: simple 2: bottombar 3: drawer"
read appId

# クリーンビルドしてapkファイルを端末へインストール
case $appId in  
  1) ./gradlew clean "installSimple${buildType}" ;;
  2) ./gradlew clean "installBottombar${buildType}" ;;
  3) ./gradlew clean "installDrawer${buildType}" ;;
  *)
    echo "${ESC}[31mPlease choose 1 or 2 or 3. app install failed.${ESC}[m"
    exit 1
    ;;
esac

# apkファイルの格納ディレクトリを開く
open app/build/outputs/apk/

# コマンド終了時にターミナルを閉じる
osascript -e 'tell application "Terminal" to quit' & exit