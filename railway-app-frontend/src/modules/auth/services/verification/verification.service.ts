import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VerifyRequest } from 'src/modules/shared/model/verification';
import { ConfigService } from 'src/modules/shared/services/config/config.service';

@Injectable({
  providedIn: 'root'
})
export class VerificationService {

  constructor(private configService: ConfigService, private http: HttpClient) { }

  createVerifyRequest(verifyId: string | null, securityCode: string): VerifyRequest {
    return {
      verifyId: verifyId,
      securityCode: securityCode,
    };
  }

  verify(verifyRequest: VerifyRequest): Observable<boolean> {
    return this.http.put<boolean>(
      this.configService.ACTIVATE_ACCOUNT_URL,
      verifyRequest
    );
  }

  sendCodeAgain(verifyId: string | null) {
    return this.http.post(
      this.configService.SEND_CODE_AGAIN_URL,
      verifyId
    );
  }

}
