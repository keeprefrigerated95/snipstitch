# snipstitch

Snip-Stitch takes clips from a video of your choosing and then concatenates them into a new video

****************************XML Files****************************
XML files are used to indicate which clips from the video you want to keep, not the ones that will be cut out.
It must be formatted like so:

<edits>
    <snippet>
        <description>a description of the clip</description>
        <sHr>the start-hour of the clip</sHr> <sMin>start minute</sMin> <sSec>start second</sSec>
        <eHr>the end hour</ehr> <eMin>the end minute</eMin> <eSec>The end second</eSec>
    </snippet>
    <snippet>
        <description>one more example</description>
        <sHr>0</sHr> <sMin>4</sMin> <sSec>20</sSec>
        <eHr>1</eHr> <eMin>2</eMin> <eSec>1</eSec>
    </snippets>
</edits>

You can view and edit XML files in your basic Notepad or Text editor, or something more intuitive like notepad++ or visual studio code

****************************ffmpeg****************************
You need ffmpeg for this to work. If you have ffmpeg and Snip-Stitch cannot find it, you'll have to type in its filepath directly.