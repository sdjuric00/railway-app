import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

//Interceptor presrece requestove koji se salju sa fronta i moze da presrece i odgovore koji se primaju
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    request = request.clone({
      withCredentials: true,
      setHeaders: {
        "Authorization": `Bearer ${localStorage.getItem("token") || ''}`,
        "Access-Control-Allow-Credentials": "true",
        "Access-Control-Allow-Origin": "http://localhost:4200"
      }
    });
    return next.handle(request);
  }
}