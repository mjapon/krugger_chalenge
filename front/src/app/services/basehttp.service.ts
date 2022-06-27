import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from "src/environments/environment";


export class BaseHttpService {
  protected urlEndpoint: string;
  private baseUrlEndpoint = environment.baseUrlEndPoint;

  constructor(path: string,
    protected http: HttpClient) {
    this.urlEndpoint = this.baseUrlEndpoint + path;
  }

  protected getHOT(pparams?: any): any {
    const token = localStorage.getItem('token');
    return {
      headers: {
        'Authorization': `Bearer ${token}`
      },
      params: pparams || {}
    };
  }

  protected joinErrorMessages(errors: Array<string>) {
    return errors.join(',');
  }


  protected handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }




}
