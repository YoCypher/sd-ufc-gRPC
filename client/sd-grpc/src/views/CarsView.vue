<template>
  <div class="container">
      <form action="">
          <div class="inputs-form">
                <label for="idInput">ID:</label>
                <input type="text" v-model="id" class="form-control" id="idInput"  />
           </div>
           <div class="inputs-form">
                <label for="nameInput">Nome:</label>
                <input type="text" v-model="name" class="form-control" id="nameInput"  />
           </div>
           <div class="inputs-form">
                <label for="brandInput">Marca:</label>
                <input type="text" class="form-control" v-model="brand" id="brandInput"/>
           </div>
           <div class="inputs-form">
                <label for="fabricationYearInput">Ano de Fabricação:</label>
                <input type="text" class="form-control" v-model="fabricationYear" id="fabricationYearInput"/>
            </div>
            <div class="inputs-form">
                <label for="modelYearInput">Ano de Modelo:</label>
                <input type="text" class="form-control" v-model="modelYear" id="modelYearInput"/>
            </div>
            <div class="inputs-form">
                <label for="priceInput">Preço:</label>
                <input type="text" class="form-control" v-model="price" id="priceInput"/>
            </div>
            <div class="buttons-crud">
               <button type="button" v-on:click="createCar" >Cadastrar</button> 
               <button type="button" v-on:click="updateCar">Atualizar</button>
                <button type="button" v-on:click="buscaPorId" >Listar 1</button>
                 <button type="button" v-on:click="mostrarCar">Listar Todos</button>
               <button type="button" v-on:click="deleteCar">Deletar</button>  
            </div>
      </form>
     <div class="">
        <div>
          <p>{{ "ID do Carro: " + car.id }}</p>
          <p>{{ "Nome: " + car.name }}</p>
          <p>{{ "Marca: " + car.brand }}</p>
          <p>{{ "Ano de Fabricação: " + car.fabricationYear }}</p>
          <p>{{ "Ano de Modelo: " + car.modelYear }}</p>
          <p>{{ "Preço: " + car.price }}</p>
          </div>
          <div>
               <br><button type="button" class="">
               Fechar
               </button>
          </div>
     </div>
     <table class="tbjs">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Marca</th>
          <th>Ano de Fabricação</th>
          <th>Ano de Modelo</th>
          <th>Preço</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="car in cars" :key="car.id">
          <th>{{ car.id }}</th>
          <td>{{ car.name }}</td>
          <td>{{ car.brand }}</td>
          <td>{{ car.fabricationYear }}</td>
          <td>{{ car.modelYear }}</td>
          <td>{{ car.price }}</td>
        </tr>
      </tbody>
    </table>
</div>
</template>

<script>
import httpClient from '../providers/httpClient'

export default {
     name: "CarsView",
     data(){
        return{
          id: "",
          name: "",
          brand: "",
          fabricationYear: "",
          modelYear: "",
          price:"",
          car: {},
          cars: [],
          mostrarCar: false,
        };
     },
     created() {
        httpClient
          .get("/cars")
          .then((response) => {
          this.cars = response.data;
          })
          .catch(() => {
          alert("Ocorreu um error ao buscar seus dados");
          });
     },
     methods: {
          showCar(){
               httpClient.get('/cars').then((response) => {
                    this.cars = response.data;
               })
               .catch(() => {alert("Ocorreu um erro ao buscar seus dados");
               })
               this.mostrarCar = true;
          },
          buscaPorId(){
               let obj = {
                    id: this.id,
                    name: this.name,
                    brand: this.brand,
                    fabricationYear: this.fabricationYear,
                    modelYear: this.modelYear,
                    price: this.price,
               };
               if(this.id != ""){
                    httpClient.get('/cars' + "/" + this.id).then((response) => {
                         this.cars = response.data;
                    })
                    .catch(() => {alert("Ocorreu um erro ao buscar seus dados");
                    })
                    this.mostrarCar = true;
               }
          },
          createCar(){
               let obj = {
                    id: this.id,
                    name: this.name,
                    brand: this.brand,
                    fabricationYear: this.fabricationYear,
                    modelYear: this.modelYear,
                    price: this.price,
               };
               httpClient
               .post("/cars", {
                    name: this.name,
                    brand: this.brand,
                    fabricationYear: this.fabricationYear,
                    modelYear: this.modelYear,
                    price: this.price,
               })
               .then(() => {
                    this.$router.push({path: '/cars'})
               })
               .catch(() => {
                    alert("Ocorreu um erro ao salvar seu funcionário");
               });
               this.mostrarCar = true;
          },
          updateCar() {
               let obj = {
                    id: this.id,
                    name: this.name,
                    brand: this.brand,
                    fabricationYear: this.fabricationYear,
                    modelYear: this.modelYear,
                    price: this.price,
               };
               httpClient
               .put("/cars" + "/" + this.id, {
                    name: this.name,
                    brand: this.brand,
                    fabricationYear: this.fabricationYear,
                    modelYear: this.modelYear,
                    price: this.price,
               })
               .then(() => {
                    this.$router.push({ path: "/cars" });
               })
               .catch(() => {
                    alert("Ocorreu um erro ao atualizar o funcionário");
               });
               this.mostrarCar = true;
          },
          deleteCar() {
               let obj = {
                    id: this.id,
                    name: this.name,
                    brand: this.brand,
                    fabricationYear: this.fabricationYear,
                    modelYear: this.modelYear,
                    price: this.price,
               };
               httpClient
               .delete("/cars" + "/" + this.id})
               .then(() => {
                    this.cars = this.cars.filter((func) => func.id !== this.id);
               })
               .catch(() => {
                    alert("Ocorreu um error ao buscar seus dados");
               });
          },

     }
}
</script>

<style>
 .container .inputs-form input{
    width: 300px;
    margin-left: 30px;
    margin-bottom: 15px;
  }
  .container .inputs-form label{
     display: inline-flex;
     width: 160px;
     font-weight: bold;
  }
  .container .buttons-crud button{
     margin-left: 5px;
  }
  table.tbjs {
    font-family: Arial, Helvetica, sans-serif;
    display: inline-block;
    text-align: center;
    margin-top: 50px;
    border-collapse: collapse;
    align-self: center;
  }
  table.tbjs td, table.tbjs th {
    border: 1px solid #000000;
    padding: 5px 4px;
  }
  table.tbjs tbody td {
    font-size: 13px;
  }
  table.tbjs thead th {
    font-size: 15px;
    font-weight: normal;
    color: #000000;
    text-align: center;
    border-left: 2px solid #000000;
  }
  table.tbjs thead th:first-child {
    border-left: none;
  }
  
  table.tbjs tfoot td {
    font-size: 14px;    
  }

</style>