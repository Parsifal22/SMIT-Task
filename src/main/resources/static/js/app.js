const app = new Vue({
    el: '#app',
    data() {
        return {
            data: [],
            sortOrder: 'A-Z',
            searchPhoneNumber: '',
            newData: {
                name: '',
                phoneNumber: '',
                secretName: '',
            },
        };
    },
    watch: {
        sortOrder: 'fetchData', // Watch for changes in sortOrder and trigger fetchData
    },
    methods: {
        handleSortChange(event) {
            this.sortOrder = event.target.value;
        },
        handleInputChange(event) {
            const { name, value } = event.target;
            this.newData = { ...this.newData, [name]: value };
        },

        handleSearchChange(event) {
            this.searchPhoneNumber = event.target.value;

            // Send a POST request to localhost:8080/contact/setSampleData
            fetch('http://localhost:8080/contact/getFiltredData', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ searchPhoneNumber: this.searchPhoneNumber }),
            })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error(`Failed to set sample data. Status: ${response.status}`);
                    }

                    // Check if the response has content before trying to parse JSON
                    if (response.status !== 204) {
                        return response.json();
                    }

                    // No content, return an empty object or handle it accordingly
                    return {};
                })
                .then((result) => {
                    const sortedData = this.sortData(result, this.sortOrder);
                    this.data = sortedData;
                    this.newData = {
                        name: '',
                        phoneNumber: '',
                        secretName: '',
                    };
                })
                .catch((error) => {
                    console.error('Error adding data:', error);
                });
        },

        handleAddButtonClick() {
            fetch('http://localhost:8080/contact/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(this.newData),
            })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error(`Failed to add data. Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then((result) => {
                    const sortedData = this.sortData(result, this.sortOrder);
                    this.data = sortedData;
                    this.newData = {
                        name: '',
                        phoneNumber: '',
                        secretName: '',
                    };
                })
                .catch((error) => {
                    console.error('Error adding data:', error);
                });
        },
        fetchData() {
            fetch('http://localhost:8080/contact/getAllData')
                .then((response) => response.json())
                .then((result) => {
                    const sortedData = this.sortData(result, this.sortOrder);
                    this.data = sortedData;
                })
                .catch((error) => {
                    console.error('Error fetching data:', error);
                });
        },
        sortData(data, order) {
            const sortedData = [...data];

            if (order === 'A-Z') {
                sortedData.sort((a, b) => a.name.localeCompare(b.name));
            } else if (order === 'Z-A') {
                sortedData.sort((a, b) => b.name.localeCompare(a.name));
            }

            return sortedData;
        },
    },
    created() {
        this.fetchData();
    },
    template: `
    <div class="App">
      <header class="App-header">This is my task</header>
      <div class="main-content">
        <h2>Data Table</h2>
        <div class="table-container">
          <div class="sort-container">
            <label>
              Sort By Name:
              <select v-model="sortOrder" @change="handleSortChange">
                <option value="A-Z">A-Z</option>
                <option value="Z-A">Z-A</option>
              </select>
            </label>
          </div>
          <div class="search-container">
            <label>
              Search by Phone Number:
              <input type="text" v-model="searchPhoneNumber" @input="handleSearchChange" />
            </label>
          </div>
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Secret Name</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in data" :key="item.id">
                <td>{{ item.name }}</td>
                <td>{{ item.phoneNumber }}</td>
                <td>{{ item.secretName }}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="add-data-container">
          <label class="input-label">
            Name:
            <input type="text" v-model="newData.name" @input="handleInputChange" />
          </label>
          <label class="input-label">
            Phone Number:
            <input type="text" v-model="newData.phoneNumber" @input="handleInputChange" />
          </label>
          <label class="input-label">
            Secret Name:
            <input type="text" v-model="newData.secretName" @input="handleInputChange" />
          </label>
          <button @click="handleAddButtonClick">Add</button>
        </div>
      </div>
    </div>
  `,
});