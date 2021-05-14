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
    // https://webdevdesigner.com/q/create-guid-uuid-in-javascript-66424/
    const uuidv4 = () => {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            const r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }
    let id;
    const ws = new WebSocket("ws://176.190.63.49:8080/projetweb_war_exploded/changement")
    const quill = new Quill('#editor-container', {
        modules: {
            formula: true,
            syntax: true,
            toolbar: '#toolbar-container'
        },
        placeholder: 'Compose an epic...',
        theme: 'snow'
    });

    ws.onopen = event => {
        id = uuidv4();
        // console.log(id)
    };

    ws.onclose = event => {
    };

    quill.on("text-change", (delta, oldDelta, source) => {
        if(source === "user")
        {
            let aEnvoyer = {
                userId: id,
                delta: delta.ops
            }
            // let opsJSON = JSON.stringify(delta.ops)
            aEnvoyer = JSON.stringify(aEnvoyer)
            // console.table(aEnvoyer)
            ws.send(aEnvoyer)
        }
    })

    ws.onmessage = event => {
        let data = event.data;
        let pData = JSON.parse(data)
        // console.table(pData)

        let idMsg = pData.userId
        console.log(idMsg)
        let msg = pData.delta

        console.table(msg)

        if(id !== idMsg)
        {
            quill.updateContents(msg)
        }
        // console.log("Id : " + id + "message : " + msg)
        // console.log(pData)
        // quill.updateContents(data)
    }
</script>

</body>
</html>