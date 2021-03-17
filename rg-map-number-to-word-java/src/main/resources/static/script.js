(function () {

    const errorTextArea = document.getElementById('errorTextArea');
    const displayTextArea = document.getElementById("displayTextArea");
    let displayTextAreaContainer = document.getElementById("displayTextAreaContainer");
    const convertButton = document.getElementById('convertButton');
    const numberField = document.getElementById('numberField');

    const clearPage = () => {
        errorTextArea.hidden = true;
        displayTextArea.style.display = "none";
        displayTextAreaContainer.value = "";
    }

    const displayData = (data) => {
        displayTextAreaContainer.className = "textAreaContain";
        displayTextAreaContainer.hidden = false;
        displayTextArea.style.display = "block";
        let prettyData = JSON.stringify(data, undefined, 4);
        displayTextAreaContainer.value = prettyData;
    }

    const convertNumberToWord = async () => {
        clearPage()
        // just cheat a little bit to get the local url ;-)
        let currentlocation = window.location.href;
        const currentDomain = (currentlocation.indexOf("index.html") > -1)
            ? currentlocation.slice(0, -10) : currentlocation;
        const apiUrl = `${currentDomain}convert?myNumber=${numberField.value}`;

        try {
            const response = await fetch(apiUrl);
            const data = await response.json();
            if (!response.ok) {
                throw new Error(data.message);
            }
            displayData(data);
        } catch (err) {
            errorTextArea.innerText = err;
            errorTextArea.hidden = false;
        }
    }

    convertButton.addEventListener('click', convertNumberToWord);

})();
