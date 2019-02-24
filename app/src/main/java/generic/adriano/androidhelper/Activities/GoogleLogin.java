package generic.adriano.androidhelper.Activities;

import android.accounts.Account;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import generic.adriano.androidhelper.R;

public class GoogleLogin extends AppCompatActivity {

    GoogleSignInOptions googleSignInOptions;
    public static GoogleSignInClient googleSignInClient;
    SignInButton sibSignIn;
    Integer RC_SIGN_IN = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        // Link elements
        sibSignIn = findViewById(R.id.sibSignIn);
        sibSignIn.setSize(SignInButton.SIZE_STANDARD);

        // Elements listeners
        sibSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });
    }

    private void SignIn(){
        googleSignInClient.signOut();
        Intent signIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            HandleSignInResult(task);
        }
    }

    private void HandleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            Log.i("LOGN.GOOGLE.HANDLE", account.getEmail());
            Log.i("LOGN.GOOGLE.HANDLE", account.getDisplayName());
            Log.i("LOGN.GOOGLE.HANDLE", account.getAccount().name);
            Log.i("LOGN.GOOGLE.HANDLE", account.getId());
            Log.i("LOGN.GOOGLE.HANDLE", account.getIdToken());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            // TODO: Set up project on Google Developer console.
            Log.e("LOGN.GOOGLE.HANDLE", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
