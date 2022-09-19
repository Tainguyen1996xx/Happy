package com.example.modelfashion.Fragment;

import static com.example.modelfashion.Utility.Constants.KEY_PRODUCT_ID;
import static com.example.modelfashion.Utility.Constants.KEY_PRODUCT_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.modelfashion.Activity.MainActivity;
import com.example.modelfashion.Activity.ProductDetailActivity;
import com.example.modelfashion.Adapter.category.CategoryAdapter;
import com.example.modelfashion.Adapter.category.ClothesAdapter;
import com.example.modelfashion.Model.Product;
import com.example.modelfashion.Model.response.my_product.MyProduct;
import com.example.modelfashion.R;
import com.example.modelfashion.Utility.KeyboardUtils;
import com.example.modelfashion.customview.SearchBar;
import com.example.modelfashion.customview.SpacesItemDecoration;
import com.example.modelfashion.network.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;

public class CategoryFragment extends Fragment {
    SearchView searchView;
    private CategoryAdapter categoryAdapter;
    private ClothesAdapter clothesAdapter;
    private RecyclerView rcvCategory, rcvClothes;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private SearchBar searchBar;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private int currentCategory = 0;
    Repository repository;

    private List<MyProduct> listTemp = new ArrayList<>();
    private List<MyProduct> listSearch = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);


        initData();
        initListener();
        return view;
    }

    private void initListener() {
        searchBar.onSearchBarClick(new SearchBar.SearchListener() {
            @Override
            public void onClearClick() {
                listSearch.clear();
                clothesAdapter.setListProduct(listTemp);
            }

            @Override
            public void afterTextChanged(String content) {
                fakeSearch(content);
            }
        });

        categoryAdapter.setClickListener((view, position) -> {
            currentCategory = position;
            categoryAdapter.highLightSelectedItem(position);
            getProductByCategory(repository, categoryAdapter.getCategory(currentCategory));
        });

        clothesAdapter.setClickListener((position, item) -> {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra(KEY_PRODUCT_ID, item.getId());
            intent.putExtra(KEY_PRODUCT_NAME, item.getProduct_name());
            startActivity(intent);
        });

        refreshLayout.setOnRefreshListener(() -> {
            getProductByCategory(repository, categoryAdapter.getCategory(currentCategory));
            getCategory(repository);
            refreshLayout.setRefreshing(false);
        });

//        KeyboardUtils.addKeyboardToggleListener(getActivity(), isVisible -> {
//            if (!isVisible){
//                ((MainActivity) requireActivity()).showBottomNavigation();
//            }else {
//                ((MainActivity) requireActivity()).hideBottomNavigation();
//            }
//        });
    }

    private void initData() {
        repository = new Repository(requireContext());
        getCategory(repository);
    }

    private void initView(View view) {
        searchBar = view.findViewById(R.id.search_bar);
//        searchView = view.findViewById(R.id.search_view);
        categoryAdapter = new CategoryAdapter();
        categoryAdapter.setListCategory(listCategory1());
        rcvCategory = view.findViewById(R.id.rcv_category);
        rcvCategory.setAdapter(categoryAdapter);
        categoryAdapter.highLightSelectedItem(currentCategory);

        rcvClothes = view.findViewById(R.id.rcv_clothes);
        clothesAdapter = new ClothesAdapter();
        rcvClothes.setAdapter(clothesAdapter);
        rcvClothes.addItemDecoration(new SpacesItemDecoration(8));

        progressBar = view.findViewById(R.id.progress_bar);
        refreshLayout = view.findViewById(R.id.refresh_layout);
    }

    private void getProductByCategory(Repository repository, String type) {
        compositeDisposable.add(repository.getProductByType(type).doOnSubscribe(disposable -> {
            // show loading
            progressBar.setVisibility(View.VISIBLE);
        })
                .doFinally(() -> {
                    // hide loading
                    progressBar.setVisibility(View.GONE);
                })
                .subscribe(productResponse -> {
                    clothesAdapter.setListProduct(productResponse);
                    listTemp = productResponse;
                }, throwable -> {
                    Toast.makeText(requireContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
                }));
    }

    private void fakeSearch(String content){
        compositeDisposable.add(repository.getProductByType(categoryAdapter.getCategory(currentCategory)).doOnSubscribe(disposable -> {
            // show loading
            progressBar.setVisibility(View.VISIBLE);
        })
                .doFinally(() -> {
                    // hide loading
                    progressBar.setVisibility(View.GONE);
                })
                .subscribe(productResponse -> {
                    List<MyProduct> myListSearch = new ArrayList<>();
                    for (MyProduct product: productResponse) {
                        if (product.getProduct_name().toLowerCase().contains(content.toLowerCase())){
                            myListSearch.add(product);
                        }
                    }
                    clothesAdapter.setListProduct(myListSearch);
                    if (content.equals("")){
                        getProductByCategory(repository,categoryAdapter.getCategory(currentCategory));
                    }
                }, throwable -> {
                    Toast.makeText(requireContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
                }));
    }

    private void getCategory(Repository repository) {
        compositeDisposable.add(repository.getAllProduct().doOnSubscribe(disposable -> {
            progressBar.setVisibility(View.VISIBLE);
        }).subscribe(myProducts -> {
            progressBar.setVisibility(View.GONE);
            HashMap<String, String> hmType = new HashMap<>();
            for (int i = 0; i < myProducts.size(); i++) {
                hmType.put(myProducts.get(i).getType(), myProducts.get(i).getType());
            }
            Set<String> key = hmType.keySet();
            List<String> arrProductType = new ArrayList<>(key);
            categoryAdapter.setListCategory(arrProductType);

            getProductByCategory(repository, categoryAdapter.getCategory(currentCategory));
        }, throwable -> {

        }));
    }

    private List<String> listCategory1() {
        ArrayList<String> list = new ArrayList();
        list.add("Ba lô");
        list.add("Quần");
        list.add("Áo");
        list.add("Giày");
        list.add("Đồ bộ");
        return list;
    }


    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}