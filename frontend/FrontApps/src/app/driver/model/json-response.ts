export class JsonResponse {
  public message: string = ''
  public success: boolean = false

  constructor(message: string, success: boolean) {
    this.message = message
    this.success = success
  }
}
