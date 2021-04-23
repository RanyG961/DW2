<!DOCTYPE html>
<html lang="en">
<head>
    <title>Full Editor - Quill Rich Text Editor</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="language" content="english">
    <meta name="viewport" content="width=device-width">

    <link rel="stylesheet" href="<c:url value="lib/katex.min.css"/>"/>
    <link rel="stylesheet" href="lib/highlight_styles/darcula.min.css"/>


    <link rel="stylesheet" href="lib/quill.snow.css"/>

    <style>
        body > #standalone-container {
            margin: 50px auto;
            max-width: 720px;
        }

        #editor-container {
            height: 564px;
        }
    </style>


</head>
<body>

    <div id="standalone-container">
        <div id="toolbar-container">
            <span class="ql-formats">
              <select class="ql-font"></select>
              <select class="ql-size"></select>
            </span>
            <span class="ql-formats">
              <button class="ql-bold"></button>
              <button class="ql-italic"></button>
              <button class="ql-underline"></button>
              <button class="ql-strike"></button>
            </span>
            <span class="ql-formats">
              <select class="ql-color"></select>
              <select class="ql-background"></select>
            </span>
            <span class="ql-formats">
              <button class="ql-script" value="sub"></button>
              <button class="ql-script" value="super"></button>
            </span>
            <span class="ql-formats">
              <button class="ql-header" value="1"></button>
              <button class="ql-header" value="2"></button>
            </span>
            <span class="ql-formats">
              <button class="ql-list" value="ordered"></button>
              <button class="ql-list" value="bullet"></button>
              <button class="ql-indent" value="-1"></button>
              <button class="ql-indent" value="+1"></button>
            </span>
            <span class="ql-formats">
              <button class="ql-direction" value="rtl"></button>
              <select class="ql-align"></select>
            </span>
            <span class="ql-formats">
              <button class="ql-blockquote"></button>
              <button class="ql-code-block"></button>
              <button class="ql-formula"></button>
            </span>
            <span class="ql-formats">
              <button class="ql-link"></button>
              <button class="ql-image"></button>
              <button class="ql-video"></button>
            </span>
                <span class="ql-formats">
              <button class="ql-clean"></button>
            </span>
        </div>
        <div id="editor-container">

        </div>
    </div>


<script src="lib/katex.min.js"></script>

<script src="lib/highlight.min.js"></script>
<script src="lib/quill.min.js"></script>

<script>
    const quill = new Quill('#editor-container', {
        modules: {
            formula: true,
            syntax: true,
            toolbar: '#toolbar-container'
        },
        placeholder: 'Compose an epic...',
        theme: 'snow'
    });
</script>

</body>
</html>