# Pi Camera Telegram Bot

Just a simple Telegram Bot, which takes pictures with the [Camera Module](https://www.raspberrypi.org/products/camera-module-v2/) and sends them to you

## How to start

First talk to the [@BotFather](https://telegram.me/BotFather) to get a token.

Make that token available as environment variable

```bash
export TELEGRAM_PICAM_BOT_TOKEN="<your_token>"
```

Run with

```bash
./gradlew run
```

Create Shadow jar

```bash
./gradlew shadowJar
# => build/libs/PiCamBot-1.0-SNAPSHOT-all.jar
```

Tested on a Pi3 with the NoIR Camera

## Acknowledgments

* [Telegram Bot Java Library](https://github.com/rubenlagus/TelegramBots)
* [JRPICam](https://github.com/Hopding/JRPiCam)
* [Shadow](https://github.com/johnrengelman/shadow)