import http from "../http-common";

class AdministratorService {
  getAllAdministrators(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/administrator/administrators`, searchDTO);
  }

  get(administratorId) {
    return this.getRequest(`/administrator/${administratorId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/administrator?field=${matchData}`, null);
  }

  addAdministrator(data) {
    return http.post("/administrator/addAdministrator", data);
  }

  update(data) {
  	return http.post("/administrator/updateAdministrator", data);
  }
  
  uploadImage(data,administratorId) {
  	return http.postForm("/administrator/uploadImage/"+administratorId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new AdministratorService();
