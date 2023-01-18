const SIZE = 9;
    let mat = [];
    for (let i = 0; i < SIZE; ++i) {
        mat[i] = [];
    }
    function prepareSolution() {
        let inputs = document.getElementsByTagName('input');
        let inputsIterator = 0;
        for (let i = 0; i < SIZE; ++i) {
            for (let j = 0; j < SIZE; ++j) {
                mat[i][j] = inputs[inputsIterator++].value;
                let found = false;
                for (let k = 1; k <= SIZE; ++k) {
                    if (mat[i][j] == k) {
                        found = true;
                    }
                }
                if (!found) {
                    document.getElementById("gameStatus").innerHTML = "I'm afraid you haven't finished the game!";
                    setTimeout(hideStatus, 5000);
                    return;
                }
            }
        }
        sendData()
    }

    function sendData() {
        $.ajax({
            traditional: true,
            type: 'POST',
            url: '/solution/check',
            data: {
                gameBoard: mat
            },
            success: function(text) {
                document.getElementById("gameStatus").innerHTML = text;
                document.getElementById("gameStatus").style.color = green;
                document.getElementById("submitButton").disabled = true;
            },
            error: function (jqXHR) {
                document.getElementById("gameStatus").innerHTML = jqXHR.status;
            }
        });
    }

    function hideStatus() {
        document.getElementById("gameStatus").innerHTML = "";
    }