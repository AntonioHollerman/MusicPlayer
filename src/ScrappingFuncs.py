from YoutubeClasses import *
from pytube import YouTube


def link_to_mp4(folder_path: str, vid: VideoWrapper) -> bool:
    try:
        # Replace 'YOUR_YOUTUBE_URL' with the actual YouTube video URL
        yt = YouTube(vid.url)

        # Download the audio in MP3 format
        video = yt.streams.filter(only_audio=True).first()
        video.download(output_path=folder_path)
        return True
    except Exception:
        return False


def playlist_to_folder(folder_path: str, url: str) -> bool:
    try:
        playlist = YoutubePlaylist(url)
    except Exception:
        return False

    for vid in playlist:
        link_to_mp4(folder_path, vid)
    return True
