#!/usr/bin/env bash
ffmpeg -y -framerate 12 -i output/frames_parallel/frame_%03d.png -i input/audio/audio_base.wav -c:v libx264 -pix_fmt yuv420p -shortest videoclip_parallel.mp4
