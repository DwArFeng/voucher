#!/bin/sh
# 程序的根目录
basedir="/usr/local/voucher"

PID=$(cat "$basedir/voucher.pid")
kill "$PID"
