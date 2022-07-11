window.addEventListener('load', function () {
    /* ---------------------- obtenemos variables globales ---------------------- */
    const form = document.forms[0];
    const nombre = document.querySelector('#inputNombre');
    const apellido = document.querySelector('#inputApellido');
    const matricula = document.querySelector('#inputMatricula');
    const url = 'http://localhost:8090/dentists';
    const xhr = new XMLHttpRequest();

    /* -------------------------------------------------------------------------- */
    /*            FUNCIÓN 1: Escuchamos el submit y preparamos el envío           */
    /* -------------------------------------------------------------------------- */
    function onRequestHandler() {
        if(this.status === 200){
            console.log(this.response);
        }
    }

    xhr.addEventListener('load', onRequestHandler);
    xhr.open('POST', url);
    xhr.send();
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        
        //creamos el cuerpo de la request
        const payload = {
            name: nombre.value,
            lastname: apellido.value, 
            register: matricula.value,
        };
        //configuramos la request del Fetch
        const settings = {
            method: 'POST',
            body: JSON.stringify(payload),
            headers: {
                'Content-Type': 'application/json',

            }
        };
        //lanzamos la consulta de login a la API
        realizarRegister(settings);

        //limpio los campos del formulario
        form.reset();
    });

    /* -------------------------------------------------------------------------- */
    /*                    FUNCIÓN 2: Realizar el signup [POST]                    */
    /* -------------------------------------------------------------------------- */
    function realizarRegister(settings) {
        console.log("Lanzando la consulta a la API");
        fetch(`${url}/new`, settings)
            .then(response => {
                console.log(response);

                if (response.ok != true) {
                    alert("Alguno de los datos es incorrecto.")
                }

                return response.json();

            })
        
    };


});