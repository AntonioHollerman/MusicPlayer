from selectolax.parser import HTMLParser
from playwright.sync_api import sync_playwright


class VideoWrapper:
    def __init__(self, title, url):
        self.title = title
        self.url = url


class YoutubePlaylist:
    def __init__(self, url):
        with sync_playwright() as pw:
            browser = pw.chromium.launch(headless=True)

            page = browser.new_page()
            page.goto(url)

            page.wait_for_load_state("networkidle")
            page.evaluate("window.scrollTo(0, document.body.scrollHeight);")
            page.wait_for_selector("div[class='yt-spec-touch-feedback-shape yt-spec-touch-feedback-shape--touch-response'][aria-hidden='true']")

            html = page.content()
            page.close()

        tree = HTMLParser(html)
        self.a_tags = tree.css('a[class="yt-simple-endpoint style-scope ytd-playlist-video-renderer"][id="video-title"]')

    def __iter__(self):
        return self

    def __next__(self) -> VideoWrapper:
        if len(self.a_tags) == 0:
            raise StopIteration
        tag = self.a_tags.pop()
        title = tag.text().strip()
        url = "https://www.youtube.com" + tag.attributes['href']
        return VideoWrapper(title, url)

