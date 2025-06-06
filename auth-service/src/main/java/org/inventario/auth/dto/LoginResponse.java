@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean autenticado;
    private String mensaje;
    private String token;
}