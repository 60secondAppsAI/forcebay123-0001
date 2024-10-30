<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <administrator-table
            v-if="administrators && administrators.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:administrators="administrators"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-administrators="getAllAdministrators"
             >

            </administrator-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import AdministratorTable from "@/components/AdministratorTable";
import AdministratorService from "../services/AdministratorService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AdministratorTable,
  },
  data() {
    return {
      administrators: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllAdministrators(sortBy='administratorId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AdministratorService.getAllAdministrators(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.administrators.length) {
					this.administrators = response.data.administrators;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching administrators:", error);
        }
        
      } catch (error) {
        console.error("Error fetching administrator details:", error);
      }
    },
  },
  mounted() {
    this.getAllAdministrators();
  },
  created() {
    this.$root.$on('searchQueryForAdministratorsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAdministrators();
    })
  }
};
</script>
<style></style>
