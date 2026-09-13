@echo off
ffmpeg -y -framerate 12 -i output\frames_serial\frame_%%03d.png -i input\audio\audio_base.wav -c:v libx264 -pix_fmt yuv420p -shortest videoclip_serial.mp4
pause
